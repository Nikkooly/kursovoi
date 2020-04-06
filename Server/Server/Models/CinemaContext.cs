using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace Server.Models
{
    public partial class CinemaContext : DbContext
    {
        public CinemaContext()
        {
        }

        public CinemaContext(DbContextOptions<CinemaContext> options)
            : base(options)
        {
        }

        public virtual DbSet<CinemaInfo> CinemaInfo { get; set; }
        public virtual DbSet<FilmInfo> FilmInfo { get; set; }
        public virtual DbSet<GenreOfFilm> GenreOfFilm { get; set; }
        public virtual DbSet<HallInfo> HallInfo { get; set; }
        public virtual DbSet<PlacePrice> PlacePrice { get; set; }
        public virtual DbSet<Places> Places { get; set; }
        public virtual DbSet<Rating> Rating { get; set; }
        public virtual DbSet<Roles> Roles { get; set; }
        public virtual DbSet<Seance> Seance { get; set; }
        public virtual DbSet<Ticket> Ticket { get; set; }
        public virtual DbSet<Type> Type { get; set; }
        public virtual DbSet<UserData> UserData { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseSqlServer("Server=DESKTOP-15P21ID;Initial Catalog=Cinema;user=Nikkooly;password=1063Nick;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<CinemaInfo>(entity =>
            {
                entity.ToTable("cinemaInfo");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Adress)
                    .IsRequired()
                    .HasColumnName("adress")
                    .HasMaxLength(50);

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasColumnName("name")
                    .HasMaxLength(50);
            });

            modelBuilder.Entity<FilmInfo>(entity =>
            {
                entity.ToTable("filmInfo");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Country)
                    .HasColumnName("country")
                    .HasMaxLength(50);

                entity.Property(e => e.Description)
                    .HasColumnName("description")
                    .HasColumnType("text");

                entity.Property(e => e.Duration)
                    .HasColumnName("duration")
                    .HasMaxLength(10);

                entity.Property(e => e.GenreId).HasColumnName("genre_id");

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasColumnName("name")
                    .HasMaxLength(50);

                entity.Property(e => e.Poster)
                    .IsRequired()
                    .HasColumnName("poster")
                    .HasColumnType("image");

                entity.Property(e => e.Year).HasColumnName("year");

                entity.HasOne(d => d.Genre)
                    .WithMany(p => p.FilmInfo)
                    .HasForeignKey(d => d.GenreId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__filmInfo__genre___1BFD2C07");
            });

            modelBuilder.Entity<GenreOfFilm>(entity =>
            {
                entity.ToTable("genreOfFilm");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Genre)
                    .IsRequired()
                    .HasColumnName("genre")
                    .HasMaxLength(20);
            });

            modelBuilder.Entity<HallInfo>(entity =>
            {
                entity.ToTable("hallInfo");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.CinemaId).HasColumnName("cinema_id");

                entity.Property(e => e.Hall)
                    .IsRequired()
                    .HasColumnName("hall")
                    .HasMaxLength(50);

                entity.Property(e => e.Places).HasColumnName("places");

                entity.HasOne(d => d.Cinema)
                    .WithMany(p => p.HallInfo)
                    .HasForeignKey(d => d.CinemaId)
                    .HasConstraintName("FK__hallInfo__cinema__1920BF5C");
            });

            modelBuilder.Entity<PlacePrice>(entity =>
            {
                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Place).HasColumnName("place");

                entity.Property(e => e.Price).HasColumnName("price");

                entity.Property(e => e.SeanceId).HasColumnName("seance_id");

                entity.Property(e => e.Status).HasColumnName("status");

                entity.HasOne(d => d.PlaceNavigation)
                    .WithMany(p => p.PlacePrice)
                    .HasForeignKey(d => d.Place)
                    .HasConstraintName("FK__PlacePric__place__2B3F6F97");

                entity.HasOne(d => d.Seance)
                    .WithMany(p => p.PlacePrice)
                    .HasForeignKey(d => d.SeanceId)
                    .HasConstraintName("FK__PlacePric__seanc__2C3393D0");
            });

            modelBuilder.Entity<Places>(entity =>
            {
                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.HallId).HasColumnName("hall_id");

                entity.Property(e => e.Place).HasColumnName("place");

                entity.Property(e => e.Type).HasColumnName("type");

                entity.HasOne(d => d.Hall)
                    .WithMany(p => p.PlacesNavigation)
                    .HasForeignKey(d => d.HallId)
                    .HasConstraintName("FK__Places__hall_id__276EDEB3");

                entity.HasOne(d => d.TypeNavigation)
                    .WithMany(p => p.Places)
                    .HasForeignKey(d => d.Type)
                    .HasConstraintName("FK__Places__type__286302EC");
            });

            modelBuilder.Entity<Rating>(entity =>
            {
                entity.ToTable("rating");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.FilmId).HasColumnName("film_id");

                entity.Property(e => e.Rating1).HasColumnName("rating");

                entity.HasOne(d => d.Film)
                    .WithMany(p => p.Rating)
                    .HasForeignKey(d => d.FilmId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__rating__film_id__1ED998B2");
            });

            modelBuilder.Entity<Roles>(entity =>
            {
                entity.ToTable("roles");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Role)
                    .IsRequired()
                    .HasColumnName("role")
                    .HasMaxLength(10);
            });

            modelBuilder.Entity<Seance>(entity =>
            {
                entity.ToTable("seance");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Date)
                    .HasColumnName("date")
                    .HasColumnType("date");

                entity.Property(e => e.FilmId).HasColumnName("film_id");

                entity.Property(e => e.HallId).HasColumnName("hall_id");

                entity.Property(e => e.Time).HasColumnName("time");

                entity.HasOne(d => d.Film)
                    .WithMany(p => p.Seance)
                    .HasForeignKey(d => d.FilmId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__seance__film_id__22AA2996");

                entity.HasOne(d => d.Hall)
                    .WithMany(p => p.Seance)
                    .HasForeignKey(d => d.HallId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__seance__hall_id__21B6055D");
            });

            modelBuilder.Entity<Ticket>(entity =>
            {
                entity.ToTable("ticket");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.PlacePrice).HasColumnName("place_price");

                entity.Property(e => e.UserId).HasColumnName("user_id");

                entity.HasOne(d => d.PlacePriceNavigation)
                    .WithMany(p => p.Ticket)
                    .HasForeignKey(d => d.PlacePrice)
                    .HasConstraintName("FK__ticket__place_pr__300424B4");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.Ticket)
                    .HasForeignKey(d => d.UserId)
                    .HasConstraintName("FK__ticket__user_id__2F10007B");
            });

            modelBuilder.Entity<Type>(entity =>
            {
                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Type1)
                    .IsRequired()
                    .HasColumnName("type")
                    .HasMaxLength(10);
            });

            modelBuilder.Entity<UserData>(entity =>
            {
                entity.ToTable("userData");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Email)
                    .IsRequired()
                    .HasColumnName("email")
                    .HasMaxLength(50);

                entity.Property(e => e.Login)
                    .IsRequired()
                    .HasColumnName("login")
                    .HasMaxLength(50);


                entity.Property(e => e.Password)
                    .IsRequired()
                    .HasColumnName("password")
                    .HasMaxLength(50);

                entity.Property(e => e.RoleId).HasColumnName("role_id");


                entity.HasOne(d => d.Role)
                    .WithMany(p => p.UserData)
                    .HasForeignKey(d => d.RoleId)
                    .HasConstraintName("FK__userData__role_i__145C0A3F");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
