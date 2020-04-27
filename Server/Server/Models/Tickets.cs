using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class Tickets
    {
        public Tickets()
        {
            SaledTickets = new HashSet<SaledTickets>();
        }

        public Guid Id { get; set; }
        public int Place { get; set; }
        public double Price { get; set; }
        public bool Status { get; set; }
        public Guid SeanceId { get; set; }

        public virtual Seance Seance { get; set; }
        public virtual ICollection<SaledTickets> SaledTickets { get; set; }
    }
}
