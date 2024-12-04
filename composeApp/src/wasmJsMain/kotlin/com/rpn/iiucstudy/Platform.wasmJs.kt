package com.rpn.iiucstudy

import com.rpn.iiucstudy.presentation.utils.Type

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()
actual fun getType(): Type = Type.Browser