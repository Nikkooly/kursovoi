using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class HallInfo
    {
        public HallInfo()
        {
            PlacesNavigation = new HashSet<Places>();
            Seance = new HashSet<Seance>();
        }

        public int Id { get; set; }
        public int CinemaId { get; set; }
        public string Name { get; set; }
        public int Places { get; set; }

        public virtual CinemaInfo Cinema { get; set; }
        public virtual ICollection<Places> PlacesNavigation { get; set; }
        public virtual ICollection<Seance> Seance { get; set; }
    }
}
