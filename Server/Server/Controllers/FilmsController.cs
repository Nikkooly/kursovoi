using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.VisualBasic;
using Newtonsoft.Json;
using Server.Models;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Server.Controllers
{

    [Route("api/[controller]")]
    public class FilmsController : Controller
    {
        CinemaContext cinemaContext = new CinemaContext();
          // GET: api/<controller>
          [HttpGet]
           public string Get()
           {
             FilmInfo film = new FilmInfo();
            return JsonConvert.SerializeObject(cinemaContext.FilmInfo.Select(x => new { x.Id, x.Name, x.Poster, x.Year, x.Country, x.Description, x.Duration, x.Genre}));
         }

        // GET api/<controller>/5
         [HttpGet("{id}")]
         public string Get(int id)
         {
            if(cinemaContext.FilmInfo.Any(x => x.Id == id))
            {
                try
                {
                    return JsonConvert.SerializeObject(
                        cinemaContext.FilmInfo
                        .Select(x => new { x.Id, x.Name, x.Poster, x.Year, x.Country, x.Description, x.Duration, x.Genre })
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
                return JsonConvert.SerializeObject("Фильм не найден");
            }
        }

        // POST api/<controller>
        [HttpPost]
        public string Post([FromBody]FilmInfo value)
        {
            if (!cinemaContext.FilmInfo.Any(film => film.Name.Equals(value.Name)))
            {
                FilmInfo film = new FilmInfo();
                film.Name = value.Name;
                film.Year = value.Year;
                film.Country = value.Country;
                film.Duration = value.Duration;
                film.Genre = value.Genre;
                film.Description = value.Description;
                film.Poster = value.Poster;
                try
                {
                    cinemaContext.Add(film);
                    cinemaContext.SaveChanges();
                    return JsonConvert.SerializeObject("Фильм успешно добавлен");
                }
                catch (Exception ex)
                {
                    return JsonConvert.SerializeObject(ex.Message);
                }
            }
            else
            {
                return JsonConvert.SerializeObject("Такой фильм уже добавлен!");

            }
        }

        // PUT api/<controller>/5
        [HttpPut("{id}")]
        public string Put(int id, [FromBody]FilmInfo value)
        {
                FilmInfo film = cinemaContext.FilmInfo.Where(u => u.Id.Equals(id)).First();
                film.Name = value.Name;
                film.Year = value.Year;
                film.Country = value.Country;
                film.Duration = value.Duration;
                film.Genre = value.Genre;
                film.Description = value.Description;
                film.Poster = value.Poster;
                try
                {
                    cinemaContext.Update(film);
                    cinemaContext.SaveChanges();
                    return JsonConvert.SerializeObject("Данные успешно изменены");
                }
                catch (Exception ex)
                {
                    return JsonConvert.SerializeObject(ex.Message);
                }
        }
        [HttpDelete("{id}")]
        public string Delete(int id)
        {
            FilmInfo film= cinemaContext.FilmInfo.FirstOrDefault(x => x.Id == id);
            if (film == null)
            {
                return JsonConvert.SerializeObject("Фильм не найден!");
            }
            cinemaContext.FilmInfo.Remove(film);
             cinemaContext.SaveChangesAsync();
            return JsonConvert.SerializeObject("Успешно удален!");
        }
    }
}
