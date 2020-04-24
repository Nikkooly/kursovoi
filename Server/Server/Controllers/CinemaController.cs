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
    public class CinemaController : Controller
    {
        CinemaContext cinemaContext = new CinemaContext();
        // GET: api/<controller>
        [HttpGet]
        public string Get()
        {
            return JsonConvert.SerializeObject(cinemaContext.CinemaInfo.Select(x => new { x.Id, x.Name, x.Adress }));
        }

        // GET api/<controller>/5
        [HttpGet("{id}")]
        public string Get(int id)
        {
            if (cinemaContext.CinemaInfo.Any(x => x.Id == id))
            {
                try
                {
                    return JsonConvert.SerializeObject(
                        cinemaContext.CinemaInfo
                        .Select(x => new { x.Id, x.Name, x.Adress})
                        .Where(u => u.Id.Equals(id))
                        .First());
                }
                catch (Exception ex)
                {
                    return JsonConvert.SerializeObject(ex.Message);
                }
            }
            else
            {
                return JsonConvert.SerializeObject("Кинотеатр не найден");
            }
        }

        // POST api/<controller>
        [HttpPost]
        public string Post([FromBody]CinemaInfo value)
        {
            if (!cinemaContext.CinemaInfo.Any(cinema => cinema.Name.Equals(value.Name)))
            {
                CinemaInfo cinema = new CinemaInfo();
                cinema.Name = value.Name;
                cinema.Adress = value.Adress;
                try
                {
                    cinemaContext.Add(cinema);
                    cinemaContext.SaveChanges();
                    return JsonConvert.SerializeObject("Кинотеатр успешно добавлен");
                }
                catch (Exception ex)
                {
                    return JsonConvert.SerializeObject(ex.Message);
                }
            }
            else
            {
                return JsonConvert.SerializeObject("Такой кинотеатр уже существует!");

            }
        }

        // PUT api/<controller>/5
        [HttpPut("{id}")]
        public string Put(int id, [FromBody]CinemaInfo value)
        {
            CinemaInfo cinema = cinemaContext.CinemaInfo.Where(u => u.Id.Equals(id)).First();
            cinema.Name = value.Name;
            cinema.Adress = value.Adress;

            try
            {
                cinemaContext.Update(cinema);
                cinemaContext.SaveChanges();
                return JsonConvert.SerializeObject("Данные успешно изменены");
            }
            catch (Exception ex)
            {
                return JsonConvert.SerializeObject(ex.Message);
            }
        }

        // DELETE api/<controller>/5
        [HttpDelete("{id}")]
        public string Delete(int id)
        {
            CinemaInfo cinema = cinemaContext.CinemaInfo.FirstOrDefault(x => x.Id == id);
            if (cinema == null)
            {
                return JsonConvert.SerializeObject("Кинотеатр не найден!");
            }
            cinemaContext.CinemaInfo.Remove(cinema);
            cinemaContext.SaveChangesAsync();
            return JsonConvert.SerializeObject("Успешно удален!");
        }
    }
}
