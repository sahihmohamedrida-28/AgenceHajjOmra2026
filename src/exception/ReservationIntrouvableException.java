package exception;

public class ReservationIntrouvableException extends AgenceException {
    public ReservationIntrouvableException(String idReservation) {
        super("Reservation " + idReservation + " introuvable.", "ERR003");
    }
}