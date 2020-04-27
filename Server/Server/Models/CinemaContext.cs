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
        public virtual DbSet<HallInfo> HallInfo { get; set; }
        public virtual DbSet<Rating> Rating { get; set; }
        public virtual DbSet<Roles> Roles { get; set; }
        public virtual DbSet<SaledTickets> SaledTickets { get; set; }
        public virtual DbSet<Seance> Seance { get; set; }
        public virtual DbSet<Tickets> Tickets { get; set; }
        public virtual DbSet<UserData> UserData { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseSqlServer("Server=DESKTOP-15P21ID;Initial Catalog=Cinema;user=Nikkooly;password=1063Nick;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<CinemaInfo>(entity =>
            {
                entity.ToTable("cinema_info");

                entity.Property(e => e.Id)
                    .HasColumnName("id")
                    .ValueGeneratedNever();

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
                entity.ToTable("film_info");

                entity.Property(e => e.Id)
                    .HasColumnName("id")
                    .ValueGeneratedNever();

                entity.Property(e => e.Country)
                    .HasColumnName("country")
                    .HasMaxLength(50);

                entity.Property(e => e.Description)
                    .HasColumnName("description")
                    .HasColumnType("text");

                entity.Property(e => e.Duration).HasColumnName("duration");

                entity.Property(e => e.Genre)
                    .IsRequired()
                    .HasColumnName("genre")
                    .HasMaxLength(50);

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasColumnName("name")
                    .HasMaxLength(50);

                entity.Property(e => e.Poster)
                    .IsRequired()
                    .HasColumnName("poster");

                entity.Property(e => e.Year).HasColumnName("year");
            });

            modelBuilder.Entity<HallInfo>(entity =>
            {
                entity.ToTable("hall_info");

                entity.Property(e => e.Id)
                    .HasColumnName("id")
                    .ValueGeneratedNever();

                entity.Property(e => e.CinemaId).HasColumnName("cinema_id");

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasColumnName("name")
                    .HasMaxLength(50);

                entity.Property(e => e.Places).HasColumnName("places");

                entity.HasOne(d => d.Cinema)
                    .WithMany(p => p.HallInfo)
                    .HasForeignKey(d => d.CinemaId)
                    .HasConstraintName("Fk_HallInfo_Cascade");
            });

            modelBuilder.Entity<Rating>(entity =>
            {
                entity.ToTable("rating");

                entity.Property(e => e.Id)
                    .HasColumnName("id")
                    .ValueGeneratedNever();

                entity.Property(e => e.FilmId).HasColumnName("film_id");

                entity.Property(e => e.Rating1).HasColumnName("rating");

                entity.HasOne(d => d.Film)
                    .WithMany(p => p.Rating)
                    .HasForeignKey(d => d.FilmId)
                    .HasConstraintName("Fk_Rating_Film_id__Cascade");
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

            modelBuilder.Entity<SaledTickets>(entity =>
            {
                entity.ToTable("saled_tickets");

                entity.Property(e => e.Id)
                    .HasColumnName("id")
                    .ValueGeneratedNever();

                entity.Property(e => e.TicketId).HasColumnName("ticket_id");

                entity.Property(e => e.UserId).HasColumnName("user_id");

                entity.HasOne(d => d.Ticket)
                    .WithMany(p => p.SaledTickets)
                    .HasForeignKey(d => d.TicketId)
                    .HasConstraintName("Fk_Saled_tickets_ticket_cascade");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.SaledTickets)
                    .HasForeignKey(d => d.UserId)
                    .HasConstraintName("Fk_Saled_tickets_user_id___cascade");
            });

            modelBuilder.Entity<Seance>(entity =>
            {
                entity.ToTable("seance");

                entity.Property(e => e.Id)
                    .HasColumnName("id")
                    .ValueGeneratedNever();

                entity.Property(e => e.Date)
                    .IsRequired()
                    .HasColumnName("date")
                    .HasMaxLength(20);

                entity.Property(e => e.FilmId).HasColumnName("film_id");

                entity.Property(e => e.HallId).HasColumnName("hall_id");

                entity.Property(e => e.Time)
                    .IsRequired()
                    .HasColumnName("time")
                    .HasMaxLength(10);

                entity.HasOne(d => d.Film)
                    .WithMany(p => p.Seance)
                    .HasForeignKey(d => d.FilmId)
                    .HasConstraintName("Fk_Seance_Film_id__Cascade");

                entity.HasOne(d => d.Hall)
                    .WithMany(p => p.Seance)
                    .HasForeignKey(d => d.HallId)
                    .HasConstraintName("Fk_Seance_Hall_id___Cascade");
            });

            modelBuilder.Entity<Tickets>(entity =>
            {
                entity.ToTable("tickets");

                entity.Property(e => e.Id)
                    .HasColumnName("id")
                    .ValueGeneratedNever();

                entity.Property(e => e.Place).HasColumnName("place");

                entity.Property(e => e.Price).HasColumnName("price");

                entity.Property(e => e.SeanceId).HasColumnName("seance_id");

                entity.Property(e => e.Status).HasColumnName("status");

                entity.HasOne(d => d.Seance)
                    .WithMany(p => p.Tickets)
                    .HasForeignKey(d => d.SeanceId)
                    .HasConstraintName("Fk_Place_price_seance_id_Cascade");
            });

            modelBuilder.Entity<UserData>(entity =>
            {
                entity.ToTable("user_data");

                entity.Property(e => e.Id)
                    .HasColumnName("id")
                    .ValueGeneratedNever();

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
                    .HasConstraintName("FK__user_data__role___145C0A3F");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
