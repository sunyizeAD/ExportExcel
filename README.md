# ExportExcel
- Android导出Excel表格Demo


Android10 存储策略修改，调用createNewFile报错java.io.IOException: Permission denied
申请权限  
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
仍然报错
解决方法 
选择停用分区存储
  警告：明年，主要平台版本将要求所有应用都使用分区存储，无论应用的目标 SDK 级别是多少。因此，您应该提前确保您的应用能够使用分区存储。为此，请确保针对搭载 Android 10（API 级别 29）及更高版本的设备启用了该行为。
  在您的应用完全兼容分区存储之前，您可以根据应用的目标 SDK 级别或 requestLegacyExternalStorage 清单属性，暂时选择停用分区存储：
  
  以 Android 9（API 级别 28）或更低版本为目标平台。
  如果以 Android 10 或更高版本为目标平台，请在应用的清单文件中将 requestLegacyExternalStorage 的值设为 true：
  
      <manifest ... >
        <!-- This attribute is "false" by default on apps targeting
             Android 10 or higher. -->
        <application android:requestLegacyExternalStorage="true" ... >
          ...
        </application>
      </manifest>
      
  
  注意：如果某个应用在安装时启用了传统外部存储，则该应用会保持此模式，直到卸载为止。无论设备后续是否升级为搭载 Android 10 或更高版本，或者应用后续是否更新为以 Android 10 或更高版本为目标平台，此兼容性行为均适用。
  要测试以 Android 9 或更低版本为目标平台的应用在使用分区存储时的行为，您可以通过将 requestLegacyExternalStorage 的值设为 false 来选择启用该行为。
  谷歌文档：https://developer.android.google.cn/training/data-storage/files/external-scoped
