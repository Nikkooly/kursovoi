using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class UserData
    {
        public UserData()
        {
            Ticket = new HashSet<Ticket>();
        }

        public int Id { get; set; }
        public string Login { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public int? RoleId { get; set; }

        public virtual Roles Role { get; set; }
        public virtual ICollection<Ticket> Ticket { get; set; }
    }
}
