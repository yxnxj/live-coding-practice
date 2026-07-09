package tripservicekata

import tripservicekata.trip.Trip
import tripservicekata.trip.TripService
import tripservicekata.user.User

class TestTripService: TripService() {
    private var loggedUser: User? = null

    fun login(user: User) {
        loggedUser = user
    }

    override fun getLoggedUser(): User? {
        return loggedUser
    }

    override fun findTripsByUser(user: User): List<Trip> {
        return user.trips.toList()
    }
}
