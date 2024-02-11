package API
import androidx.*
public class RequestPostText (
   private var user_id : Int = 0 ,
    private var text: String? = null
)
{

   fun setUser_id(user_id : Int)  {
           this.user_id = user_id
   }
    fun setText(text: String) {
        this.text = text
    }
}
