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
    public class HallController : Controller
    {
        CinemaContext cinemaContext = new CinemaContext();
        // GET: api/<controller>
        [HttpGet]
        public string Get()
        {
            return JsonConvert.SerializeObject(cinemaContext.CinemaInfo.Select(x => new { x.Id, x.Name, x.Adress}));
        }

        // GET api/<controller>/5
        [HttpGet("{id}")]
        public string Get(string id)
        {
            if (cinemaContext.CinemaInfo.Any(x => x.Id.ToString().Equals(id)))
            {
                try
                {
                    return JsonConvert.SerializeObject(
                        cinemaContext.HallInfo
                        .Select(x => new { x.Id, x.Name, x.Places}));
                }
                catch (Exception ex)
                {
                    return JsonConvert.SerializeObject(ex.Message);
                }
            }
            else
            {
                return JsonConvert.SerializeObject("Зал не найден");
            }
        }

        // POST api/<controller>
        [HttpPost]
        public string Post([FromBody]HallInfo value)
        {
            if (!cinemaContext.HallInfo.Any(hall => hall.Name.Equals(value.Name)))
            {
                HallInfo hall = new HallInfo();
                hall.CinemaId = value.CinemaId;
                hall.Name = value.Name;
                hall.Places = value.Places;
                hall.Id = Guid.NewGuid();
                try
                {
                    cinemaContext.Add(hall);
                    cinemaContext.SaveChanges();
                    return JsonConvert.SerializeObject("Зал успешно добавлен");
                }
                catch (Exception ex)
                {
                    return JsonConvert.SerializeObject(ex.Message);
                }
            }
            else
            {
                return JsonConvert.SerializeObject("Такой зал уже существует!");

            }
        }

        // PUT api/<controller>/5
        [HttpPut("{id}")]
        public void Put(string id, [FromBody]string value)
        {
        }

        // DELETE api/<controller>/5
        [HttpDelete("{id}")]
        public string Delete(string id)
        {
            HallInfo hall = cinemaContext.HallInfo.FirstOrDefault(x => x.Id.ToString().Equals(id));
            if (hall == null)
            {
                return JsonConvert.SerializeObject("Зал не найден!");
            }
            cinemaContext.HallInfo.Remove(hall);
            cinemaContext.SaveChangesAsync();
            return JsonConvert.SerializeObject("Успешно удален!");
        }
    }
}
