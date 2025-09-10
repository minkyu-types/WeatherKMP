package org.dosys.project

import platform.UIKit.UIDevice

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Platform actual constructor() {
    actual val name: String = UIDevice.currentDevice.systemName + " " + UIDevice.currentDevice.systemVersion
}