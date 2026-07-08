package tripservicekata

import tripservicekata.exception.UserNotLoggedInException
import tripservicekata.trip.Trip
import tripservicekata.user.User

fun main() {
    TripServiceTest().test()
    println("All tests passed")
}

class TripServiceTest() {
    fun test(){
        this.throwsWhenUserIsNotLoggedIn()
        this.returnsEmptyListWhenUsersAreNotFriends()
        this.returnsTripsWhenUsersAreFriends()
    }

    fun throwsWhenUserIsNotLoggedIn() {
        val tripService = TestTripService()

        val targetUser = User("target")

        assertThrows<UserNotLoggedInException> {
            tripService.getTripsByUser(targetUser)
        }
    }

    fun returnsEmptyListWhenUsersAreNotFriends() {
        val tripService = TestTripService()

        val loggedUser = User("logged")
        val targetUser = User("target")
        tripService.login(loggedUser)

        val trips = tripService.getTripsByUser(targetUser)

        assertTrue(trips.isEmpty(),"Expected empty list of trips when users are not friends")
    }

    fun returnsTripsWhenUsersAreFriends() {
        val tripService = TestTripService()

        val loggedUser = User("logged")
        tripService.login(loggedUser)

        val targetUser = User("target")
        targetUser.addFriend(loggedUser)
        targetUser.addTrip(Trip("Paris"))

        val trips = tripService.getTripsByUser(targetUser)

        assertEquals(1, trips.size)
        assertEquals("Paris", trips[0].name)
    }
}

inline fun <reified T : Throwable> assertThrows(block: () -> Unit) {
    try {
        block()
    } catch (actual: Throwable) {
        if (actual is T) {
            return
        }
        throw AssertionError("Expected ${T::class.qualifiedName} but got $actual", actual)
    }

    throw AssertionError("Expected ${T::class.qualifiedName} but nothing was thrown")
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}

fun assertTrue(condition: Boolean, message: String) {
    if (!condition) {
        throw AssertionError(message)
    }
}
