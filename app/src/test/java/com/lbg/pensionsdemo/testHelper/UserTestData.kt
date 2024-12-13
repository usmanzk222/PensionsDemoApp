package com.lbg.pensionsdemo.testHelper

import com.lbg.pensionsdemo.data.local.entity.UserEntity
import com.lbg.pensionsdemo.data.remote.model.User
import com.lbg.pensionsdemo.data.remote.model.toUserEntity

object UserTestData {


    fun getCharactersListFromRemote(range: IntRange = (1..5)): List<User>{
       return range.map(UserTestData::getUser)
    }

    fun getUsersListFromLocal(range: IntRange = (1..5)):List<UserEntity>{
        return range.map(UserTestData::getUser).map { it.toUserEntity() }
    }

    fun getUser(index: Int): User {
        return User(
            id = index,
            name = "Name $index",
            email = "email$index@gmail.com",
            dob = "19-${index%12 + 1}-1979",
            age = 20 + index,
            ordinalAge = "${(20 + index)}th",
            pensions = 2
        )
    }

}