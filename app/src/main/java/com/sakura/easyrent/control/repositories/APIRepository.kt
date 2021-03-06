package com.sakura.easyrent.control.repositories

import com.sakura.easyrent.control.interfaces.API
import com.sakura.easyrent.model.AccessToken
import com.sakura.easyrent.model.Contract
import com.sakura.easyrent.model.RepairRequest
import com.sakura.easyrent.model.User
import retrofit2.Response

/** [APIRepository] this is the default repository for API and DB */
class APIRepository(private val api: API) : API {

    // Method(Register):
    override suspend fun register(user: User): Response<User> = api.register(user)

    // Method(Login):
    override suspend fun login(user: User): Response<AccessToken> = api.login(user)

    // Method(Contract):
    override suspend fun contract(contract: Contract): Response<Contract> = api.contract(contract)
    override suspend fun contract(token: String): Response<List<Contract>> = api.contract(token)

    // Method(UserMetaData):
    override suspend fun userMetaData(token: String): Response<User> = api.userMetaData(token)

    // Method(RepairRequest):
    override suspend fun repairRequest(token: String, repairRequest: RepairRequest): Response<RepairRequest> = api.repairRequest(token, repairRequest)
    override suspend fun repairRequest(token: String): Response<List<RepairRequest>> = api.repairRequest(token)

}