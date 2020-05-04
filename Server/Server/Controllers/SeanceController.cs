using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Server.Models;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Server.Controllers
{
    [Route("api/[controller]")]
    public class SeanceController : Controller
    {
        CinemaContext cinemaContext = new CinemaContext();
        // GET: api/<controller>
         [HttpGet]
         public IEnumerable<string> Get()
         {
             return new string[] { "value1", "value2" };
         }
        /*
         // GET api/<controller>/5
         [HttpGet("{id}")]
         public string Get(int id)
         {
             return "value";
         }
         */
        // POST api/<controller>
        [HttpPost]
        public string Post([FromBody]SeanceData value)
        {
            Seance seance = new Seance();
            Guid guid = Guid.NewGuid();
            DateTime startDate = DateTime.ParseExact(value.StartTime, "yyyy-MM-dd HH:mm", null);
            DateTime endDate = DateTime.ParseExact(value.EndTime, "yyyy-MM-dd HH:mm", null);
            /*DateTime dateToCheck = DateTime.ParseExact(value.StartTime, "yyyy-MM-dd HH:mm", null);
            if(dateToCheck.IsInRange(cinemaContext.Seance.StartTime, seance.EndTime)==true)
            {
                return JsonConvert.SerializeObject("На данное время уже есть сеанс");
            }
            else
            {
                return JsonConvert.SerializeObject("Ok");
            } */
            if (DbFunctions.FunctionDate(startDate, endDate))
            {
                return "Ok";
            }
            else
            {
                return "ne Ok";
            }
           
               /* seance.StartTime = DateTime.ParseExact(value.StartTime, "yyyy-MM-dd HH:mm", null); 
                seance.EndTime = DateTime.ParseExact(value.EndTime, "yyyy-MM-dd HH:mm", null);
                seance.HallId = value.HallId;
                seance.FilmId = value.FilmId;
                seance.Id = guid;
                try
                {
                    cinemaContext.Add(seance);
                    cinemaContext.SaveChanges();
                    return guid.ToString();
                }
                catch (Exception ex)
                {
                    return JsonConvert.SerializeObject(ex.Message);
                }*/
        }
        // PUT api/<controller>/5
        [HttpPut("{id}")]
        public void Put(string id, [FromBody]string value)
        {
        }

        // DELETE api/<controller>/5
        [HttpDelete("{id}")]
        public void Delete(string id)
        {
        }
    }
    /*public static class DateTimeExtensions
    {
        public static bool IsInRange(this DateTime dateToCheck, DateTime startDate, DateTime endDate)
        {
            return dateToCheck >= startDate && dateToCheck < endDate;
        }
    }*/
    public static class DbFunctions
    {
        static int resultStartDate = 0;
        static int resultEndDate = 0;
        public static bool FunctionDate(DateTime startTime, DateTime endTime)
        {
          
            using (var db = new CinemaContext())
            {
                int resultStartDate = db.Seance.Count(p => startTime >p.StartTime && startTime<p.EndTime);
            }
            if (resultStartDate == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
