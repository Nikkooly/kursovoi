using System;
using System.Collections.Generic;

namespace Server.Models
{
    public partial class GenreOfFilm
    {
        public GenreOfFilm()
        {
            FilmInfo = new HashSet<FilmInfo>();
        }

        public int Id { get; set; }
        public string Genre { get; set; }

        public virtual ICollection<FilmInfo> FilmInfo { get; set; }
    }
}
