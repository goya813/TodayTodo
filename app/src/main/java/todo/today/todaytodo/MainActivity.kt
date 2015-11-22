package todo.today.todaytodo

import android.app.Activity
import android.content.Context
import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import java.util.*


public class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = TodoListAdapter(this, ArrayList<String>())
        var todoView = findViewById(R.id.todo_view) as ListView
        todoView.setAdapter(adapter)

        var inputForm = findViewById(R.id.input_form) as EditText
        var registerBtn = findViewById(R.id.register_button) as Button
        registerBtn.setOnClickListener { v ->
            val text = inputForm.getText().toString()
            adapter.add(text)
            adapter.notifyDataSetChanged()

            inputForm.setText("")
        }
    }

    public override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    public override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private class TodoListAdapter(context: Context, items: ArrayList<String>) : ArrayAdapter<String>(context, 0, items)
    {
        private val mInflater : LayoutInflater

        init
        {
            mInflater = LayoutInflater.from(context)
        }

        override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View?
        {
            var view = convertView
            val item = getItem(pos) as String
            val textView : TextView

            if (view == null){
                view = mInflater!!.inflate(R.layout.todo_row, parent, false)
            }

            textView = view!!.findViewById(R.id.todo_text) as TextView
            textView.text = item

            val removeBtn = view!!.findViewById(R.id.remove_btn) as Button
            removeBtn.setOnClickListener { v ->
                this.remove(item)
                this.notifyDataSetChanged()
            }

            return view
        }
    }
}
