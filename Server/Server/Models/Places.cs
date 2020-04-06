using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class Places
    {
        public Places()
        {
            PlacePrice = new HashSet<PlacePrice>();
        }

        public int Id { get; set; }
        public int Place { get; set; }
        public int? HallId { get; set; }
        public int? Type { get; set; }

        public virtual HallInfo Hall { get; set; }
        public virtual Type TypeNavigation { get; set; }
        public virtual ICollection<PlacePrice> PlacePrice { get; set; }
    }
}
