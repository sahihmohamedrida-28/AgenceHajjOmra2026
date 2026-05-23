package exception;

public class ReservationIntrouvableException extends AgenceException {
    public ReservationIntrouvableException(String idReservation) {
        super("La reservation " + idReservation + " n'existe pas.", "ERR003");
    }
}