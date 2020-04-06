using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class Type
    {
        public Type()
        {
            Places = new HashSet<Places>();
        }

        public int Id { get; set; }
        public string Type1 { get; set; }

        public virtual ICollection<Places> Places { get; set; }
    }
}
