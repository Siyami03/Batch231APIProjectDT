package pojos;

public class HerokuAppPostResponsePojo {

    private Integer bookingid;
    //HerOkuAppGetResponsePojo muz booking alanini tam olarak temsil ediyor
    //o yüzden bu alaninin veri tipini HerOkuAppGetResponsePojo sectik
    private HerOkuAppGetResponsePojo booking;


    public HerokuAppPostResponsePojo() {
    }

    public HerokuAppPostResponsePojo(Integer bookingid, HerOkuAppGetResponsePojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public HerOkuAppGetResponsePojo getBooking() {
        return booking;
    }

    public void setBooking(HerOkuAppGetResponsePojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "HerokuAppPostResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }


}
