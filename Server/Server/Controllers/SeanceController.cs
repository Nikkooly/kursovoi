using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
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
            int result = 0;
            Seance seance = new Seance();
            Guid guid = Guid.NewGuid();
            DateTime startDate = DateTime.ParseExact(value.StartTime.Replace(' ','T'), "yyyy-MM-ddTHH:mm:ss", null);
            DateTime endDate = DateTime.ParseExact(value.EndTime.Replace(' ', 'T'), "yyyy-MM-ddTHH:mm:ss", null);
            using (var db = new CinemaContext())
            {
               result = db.Seance.Count(p => startDate > p.StartTime && startDate < p.EndTime);
            }
            /*using (CinemaContext db = new CinemaContext())
            {
                //Microsoft.Data.SqlClient.SqlParameter paramOne = new Microsoft.Data.SqlClient.SqlParameter("@startDate", startDate);
                Microsoft.Data.SqlClient.SqlParameter paramOne = new Microsoft.Data.SqlClient.SqlParameter("@startDate", "2020-05-13T10:32:00");
                //Microsoft.Data.SqlClient.SqlParameter paramTwo = new Microsoft.Data.SqlClient.SqlParameter("@endDate", endDate);
                Microsoft.Data.SqlClient.SqlParameter paramTwo = new Microsoft.Data.SqlClient.SqlParameter("@endDate", "2020-05-13T11:32:00");
                result = db.Database.   //ExecuteSqlRaw($"SELECT [dbo].[CheckSeanceData]({0},{1})", "2020-05-13T10:32:00", "2020-05-13T10:32:00");
            }*/
            if (result == 0)
            {
                return "ok";
            }

            else
            {
                return "ne ok";
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
   
}
