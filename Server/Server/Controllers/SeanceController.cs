using System;
using System.Collections.Generic;
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
        /* [HttpGet]
         public IEnumerable<string> Get()
         {
             return new string[] { "value1", "value2" };
         }

         // GET api/<controller>/5
         [HttpGet("{id}")]
         public string Get(int id)
         {
             return "value";
         }
         */
        // POST api/<controller>
        [HttpPost]
        public string Post([FromBody]Seance value)
        {
            if (!cinemaContext.Seance.Any(seance => seance.Time.Equals(value.Time) && seance.Date.Equals(value.Date) && seance.FilmId.Equals(value.FilmId) && seance.HallId.Equals(value.HallId)))
            {
                Seance seance = new Seance();
                Guid guid = Guid.NewGuid();
                seance.Date = value.Date;
                seance.Time = value.Time;
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
                }
            }
            else
            {
                return JsonConvert.SerializeObject("Сеанс уже существует!");
            }
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
}
