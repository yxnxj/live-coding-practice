package tripservicekata.user

import tripservicekata.trip.Trip

data class User(val name: String) {
    private val mutableFriends = mutableListOf<User>()
    private val mutableTrips = mutableListOf<Trip>()

    val friends: List<User>
        get() = mutableFriends.toList()

    val trips: List<Trip>
        get() = mutableTrips.toList()

    fun addFriend(user: User) {
        mutableFriends.add(user)
    }

    fun addTrip(trip: Trip) {
        mutableTrips.add(trip)
    }
}
