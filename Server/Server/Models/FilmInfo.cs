using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class FilmInfo
    {
        public FilmInfo()
        {
            Rating = new HashSet<Rating>();
            Seance = new HashSet<Seance>();
        }

        public int Id { get; set; }
        public byte[] Poster { get; set; }
        public int Year { get; set; }
        public string Country { get; set; }
        public string Duration { get; set; }
        public int GenreId { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }

        public virtual GenreOfFilm Genre { get; set; }
        public virtual ICollection<Rating> Rating { get; set; }
        public virtual ICollection<Seance> Seance { get; set; }
    }
}
