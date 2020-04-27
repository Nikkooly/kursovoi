﻿using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class Seance
    {
        public Seance()
        {
            Tickets = new HashSet<Tickets>();
        }

        public Guid Id { get; set; }
        public string Date { get; set; }
        public string Time { get; set; }
        public Guid HallId { get; set; }
        public Guid FilmId { get; set; }

        public virtual FilmInfo Film { get; set; }
        public virtual HallInfo Hall { get; set; }
        public virtual ICollection<Tickets> Tickets { get; set; }
    }
}
