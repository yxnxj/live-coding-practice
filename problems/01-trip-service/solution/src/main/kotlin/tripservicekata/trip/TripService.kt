package tripservicekata.trip

import tripservicekata.exception.UserNotLoggedInException
import tripservicekata.session.UserSession
import tripservicekata.user.User

open class TripService {

    fun getTripsByUser(user: User): List<Trip> {
        val tripList = mutableListOf<Trip>()
        val loggedUser = this.getLoggedUser()
        var isFriend = false

        if (loggedUser != null) {
            for (friend in user.friends) {
                if (friend == loggedUser) {
                    isFriend = true
                    break
                }
            }

            if (isFriend) {
                tripList.addAll(this.findTripsByUser(user))
            }

            return tripList
        }

        throw UserNotLoggedInException()
    }

    protected open fun getLoggedUser(): User? {
        return UserSession.loggedUser
    }

    protected open fun findTripsByUser(user: User): List<Trip> {
        return TripDAO.findTripsByUser(user)
    }
}
