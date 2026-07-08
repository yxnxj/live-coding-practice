package tripservicekata.trip

import tripservicekata.user.User

object TripDAO {
    fun findTripsByUser(user: User): List<Trip> = user.trips.toList()
}