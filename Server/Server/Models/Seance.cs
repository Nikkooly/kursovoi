using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class Seance
    {
        public Seance()
        {
            PlacePrice = new HashSet<PlacePrice>();
        }

        public int Id { get; set; }
        public DateTime Date { get; set; }
        public TimeSpan Time { get; set; }
        public int HallId { get; set; }
        public int FilmId { get; set; }

        public virtual FilmInfo Film { get; set; }
        public virtual HallInfo Hall { get; set; }
        public virtual ICollection<PlacePrice> PlacePrice { get; set; }
    }
}
