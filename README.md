# SOPT27_Android

### 📌 Assignment01 20201105
### 📑 MUST - SignUpActivity
🔥 로그인 화면에서 회원가입을 누르면 이동 ***(setOnClickListener)***
```kotlin
// 회원가입
        btn_signup.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 0)
        }
```

🔥 모든 EditText에 데이터가 있으면 가입 완료 Toast,
하나라도 빈 칸이 있으면 이를 알리는 Toast. ***(isNullOrBlank())***
```kotlin
 btn_signup.setOnClickListener{
        if(et_name.text.isNullOrBlank() || et_id.text.isNullOrBlank() || et_pw.text.isNullOrBlank()) {
            toast("모든 정보를 기입해주세요.")
        } else{
            val intent = Intent(this, LoginActivity::class.java)
            ...
            toast("가입이 완료되었어요! :)")
            startActivity(intent)
        }
    }
```

🔥 비밀번호 EditText는 그 내용이 특수문자로 나타날 것. ***(inputType)***  
🔥 모든 EditText에 미리보기 문구 존재. ***(hint)***
```xml
<EditText
          ...
        android:hint="비밀번호"
        android:inputType="textPassword"
        />
```
  
  
## 📑 OPTIONAL - 화면이동
🔥 회원가입 후 이전 로그인 화면으로 돌아가기.  
🔥 방금 가입한 아이디와 비밀번호가 미리 입력되어 있을 것.  
**(startActivityForResult, setResult, onActivityResult)**  

**LoginActivity**  
: 데이터를 받아올 수 있도록 startActivityForResult로 SignUpActivity 호출  
  누가 호출했는지 알려주는 RequestCode는 0으로 지정  
```kotlin
val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 0)
```

**SignUpActivity**  
: intent에 id와 pw를 각각 "id", "pw"의 이름으로 putExtra로 달아준 뒤 LoginActivity로 복귀  
```kotiln
val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", et_id.text.toString())
            intent.putExtra("pw", et_pw.text.toString())
            toast("가입이 완료되었어요! :)")
            setResult(Activity.RESULT_OK, intent)
            finish()
```

**LoginActivity**  
: 가입 후 돌아왔을 때 id와 pw를 각각 getStringExtra를 통해 세팅  
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when (requestCode){
                0 -> {
                    et_id.setText(data!!.getStringExtra("id").toString())
                    et_pw.setText(data!!.getStringExtra("pw").toString())
                }
            }
        }
```
  
  
## 📑 OPTIONAL - 자동 로그인  
🔥 로그인 시 HomeActivity로 이동 ***startActivity***  
```kotlin
// 로그인
        btn_login.setOnClickListener{
            // 자동 로그인 체크 시 SharedPreferences에 저장
            if (switch_auto.isChecked) { ... }

            toast("로그인 성공 :)")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
```

🔥 id, pw를 기억하여 다음 로그인에서 자동 로그인하고 Toast. ***SharedPreferences***  
**Class App**  
: SharedPreferences와 에디터를 생성하는 함수 호출  
```kotlin
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Pref.init(this)
    }
}
```
**object Pref**  
: sharedEdit(에디터) 생성  
```kotlin
object Pref {
    private val PREF_NAME = "pref"

    lateinit var sharedPref : SharedPreferences
    lateinit var sharedEdit : SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    fun init(context: Context){
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedEdit = sharedPref.edit()
        sharedEdit.apply()
    }
}
```
**LoginActivity**  
: 자동 로그인 스위치 On일 시 id, pw를 저장.  
  바디 도입부에 스위치 On/Off를 체크하여 sharedPref.getString한 id, pw가 존재한다면 자동로그인
```kotlin
        // 저장된 id, pw가 있다면 자동 로그인
        if(Pref.sharedPref.getString("id", "") != null
            && Pref.sharedPref.getString("pw", "") != null)
        {
            toast("자동 로그인 성공")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        
        ...
        
        // 로그인
        btn_login.setOnClickListener{
            // 자동 로그인 체크 시 SharedPreferences에 저장
            if (switch_auto.isChecked) {
                Pref.sharedEdit.putString("id", et_id.text.toString())
                Pref.sharedEdit.putString("pw", et_pw.text.toString())
                Pref.sharedEdit.apply()
            }
        ...
        }
```
