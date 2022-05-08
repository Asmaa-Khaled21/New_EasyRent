package com.sakura.easyrent.model.states

/** [warning] for providing a fast warning validation */
fun warning(warningMessage: String): ValidationStates.Warning = ValidationStates.Warning(warningMessage)

/** [success] for providing a fast success validation */
fun success(status: Boolean): ValidationStates.Success = ValidationStates.Success(status)