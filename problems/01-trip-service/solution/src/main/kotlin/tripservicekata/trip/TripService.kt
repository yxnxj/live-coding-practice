package tripservicekata.trip

import tripservicekata.exception.UserNotLoggedInException
import tripservicekata.session.UserSession
import tripservicekata.user.User

class TripService {

    fun getTripsByUser(user: User): List<Trip> {
        val tripList = mutableListOf<Trip>()
        val loggedUser = UserSession.loggedUser
        var isFriend = false

        if (loggedUser != null) {
            for (friend in user.friends) {
                if (friend == loggedUser) {
                    isFriend = true
                    break
                }
            }

            if (isFriend) {
                tripList.addAll(TripDAO.findTripsByUser(user))
            }

            return tripList
        }

        throw UserNotLoggedInException()
    }
}
