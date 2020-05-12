using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Server.Models;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Server.Controllers
{
    [Route("api/[controller]")]
    public class PublishTickets : Controller
    {
        CinemaContext cinemaContext = new CinemaContext();
        [HttpGet("{id}")]
        public string Get(string id)
        {
            return cinemaContext.Tickets.Where(u=>u.Id.ToString().Equals(id)).Select(s=>s.Place).First().ToString();
        }
        // POST api/<controller>
        [HttpPost]
        public string Post([FromBody]BoughtTicket value)
        {
            List<Guid> placesId = value.IdPlace;
            string emailUser = value.EmailUser;
            bool status = false;
            SaledTickets saledTickets= new SaledTickets();
            StringBuilder stringBuilder = new StringBuilder();
            Tickets tickets;
            using (var transaction = cinemaContext.Database.BeginTransaction())
            {
                try
                {
                    for(int i = 0; i < placesId.Count(); i++)
                    {
                        if (!cinemaContext.SaledTickets.Any(u=> u.TicketId.Equals(placesId.ElementAt(i))))
                        {                        
                            saledTickets.TicketId = value.IdPlace.ElementAt(i);
                            saledTickets.UserId = value.IdUser;
                            saledTickets.Id = Guid.NewGuid();
                            tickets = cinemaContext.Tickets.Where(u => u.Id.Equals(placesId.ElementAt(i))).FirstOrDefault();
                            if (tickets != null)
                            {
                                tickets.Status = true;
                            }
                            else
                            {
                                return "No";
                            }
                            cinemaContext.Add(saledTickets);                           
                            cinemaContext.Update(tickets);
                            cinemaContext.SaveChanges();
                        }
                        else
                        {
                            status = false;
                            transaction.Rollback();
                            return "No";
                        }
                        stringBuilder.Append(cinemaContext.Tickets.Where(u=>u.Id.Equals(placesId.ElementAt(i))).Select(s=>s.Place.ToString()).First()+",");
                    }
                   
                    transaction.Commit();
                    status = true;
                    
                    SendEmailAsync(value.EmailUser, value.CinemaInfo, value.HallName, value.Date, value.StartTime, value.EndTime,stringBuilder.ToString()).GetAwaiter();
                  
                }
                catch (Exception ex)
                {
                    transaction.Rollback();
                }
                if (status)
                {
                    return "OK";
                }
                else
                {
                    return "No";
                }
            }
        }


        private static async Task SendEmailAsync(string toUser, string cinema,string hall,string date,string startTime,string EndTime, string places)
        {

            MailAddress from = new MailAddress("MagnifisentCinema@yandex.ru", "Magnifisent cinema");
            MailAddress to = new MailAddress(toUser);
            MailMessage m = new MailMessage(from, to);
            m.Subject = "Покупка билета";
            m.Body = $"Спасибо за покупку билетов в нашем кинотеатре. Информация по билету:<br> Кинотеатр: {cinema}  <br> Зал: {hall} <br> Дата: {date} <br> Начало сеанса: {startTime} <br> Конец сеанса: {EndTime}  <br> Места: {places} <br> QR код находится во вложении письма";
            m.IsBodyHtml = true;
            m.Attachments.Add(new Attachment("F:/kursovoi/images/qr-code.png"));
            SmtpClient smtp = new SmtpClient("smtp.yandex.by", 587);
            smtp.Credentials = new NetworkCredential("MagnifisentCinema@yandex.ru", "123456789Nick");
            smtp.EnableSsl = true;
            await smtp.SendMailAsync(m);
        }
    }
}
