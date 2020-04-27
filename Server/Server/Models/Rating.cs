using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class Rating
    {
        public Guid Id { get; set; }
        public Guid FilmId { get; set; }
        public double Rating1 { get; set; }

        public virtual FilmInfo Film { get; set; }
    }
}
