using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class Rating
    {
        public int Id { get; set; }
        public int FilmId { get; set; }
        public double Rating1 { get; set; }

        public virtual FilmInfo Film { get; set; }
    }
}
