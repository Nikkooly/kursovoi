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
    public class TicketsController : Controller
    {
        CinemaContext cinemaContext = new CinemaContext();
        // GET: api/<controller>
        [HttpGet]
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

        // POST api/<controller>
        [HttpPost]
        public string Post([FromBody]Tickets value)
        {
            Tickets tickets = new Tickets()
            {
                Id = Guid.NewGuid(),
                Place = value.Place,
                Price = value.Price,
                Status = value.Status,
                SeanceId = value.SeanceId
            };
            try
            {
                cinemaContext.Add(tickets);
                cinemaContext.SaveChanges();
                return JsonConvert.SerializeObject("Сеанс успешно добавлен");
            }
            catch (Exception ex)
            {
                return JsonConvert.SerializeObject(ex.Message);
            }

        }

        // PUT api/<controller>/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/<controller>/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
