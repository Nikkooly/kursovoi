﻿using System;
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
    public class RegisterController : Controller
    {
        CinemaContext cinemaContext = new CinemaContext();
        // POST api/<controller>
        [HttpPost]
        public string Post([FromBody]UserData value)
        {
            if (!cinemaContext.UserData.Any(user => user.Login.Equals(value.Login)))
            {
                UserData user = new UserData();  
                user.Login = value.Login;
                user.Email = value.Email;
                user.Password = value.Password;
                user.RoleId = value.RoleId;
                try
                {
                    cinemaContext.Add(user);
                    cinemaContext.SaveChanges();
                    return JsonConvert.SerializeObject("Register Succesfully");
                }
                catch (Exception ex)
                {
                    return JsonConvert.SerializeObject(ex.Message);
                }
            }
            else
            {
                return JsonConvert.SerializeObject("User exists");

            }
        }

    }
}
