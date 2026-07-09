package tripservicekata

import tripservicekata.exception.UserNotLoggedInException
import tripservicekata.session.UserSession
import tripservicekata.trip.TripService
import tripservicekata.user.User

fun main() {
    TripServiceTest().test()
    println("All tests passed")
}

class TripServiceTest {
    fun test() {
        throwsWhenUserIsNotLoggedIn()
    }

    fun throwsWhenUserIsNotLoggedIn() {
        UserSession.loggedUser = null
        val tripService = TripService()
        val targetUser = User("target")

        assertThrows<UserNotLoggedInException> {
            tripService.getTripsByUser(targetUser)
        }
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
