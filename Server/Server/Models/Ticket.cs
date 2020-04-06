using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class Ticket
    {
        public int Id { get; set; }
        public int? UserId { get; set; }
        public int? PlacePrice { get; set; }

        public virtual PlacePrice PlacePriceNavigation { get; set; }
        public virtual UserData User { get; set; }
    }
}
