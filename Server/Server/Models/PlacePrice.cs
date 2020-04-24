using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class PlacePrice
    {
        public PlacePrice()
        {
            Ticket = new HashSet<Ticket>();
        }

        public int Id { get; set; }
        public int PlaceId { get; set; }
        public int SeanceId { get; set; }
        public bool Status { get; set; }
        public double Price { get; set; }

        public virtual Places Place { get; set; }
        public virtual Seance Seance { get; set; }
        public virtual ICollection<Ticket> Ticket { get; set; }
    }
}
