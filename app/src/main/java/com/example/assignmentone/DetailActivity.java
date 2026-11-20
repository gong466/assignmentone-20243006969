package com.example.assignmentone;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Detail screen of CampusLife: shows the information for one topic.
 *
 * <p>{@link MainActivity} starts this activity with the index of the tapped topic in
 * the {@link MainActivity#EXTRA_TOPIC_INDEX} extra. That index is used to read the
 * matching title, body text and image name out of {@code res/values/arrays.xml}.
 * Because the three arrays in that file share the same order, the same index works
 * for all of them - no if statements or switch cases are needed.</p>
 */
public class DetailActivity extends AppCompatActivity {

    /** Resource type of the drawables listed in arrays.xml. */
    private static final String DRAWABLE_TYPE = "drawable";

    /** Fallback topic, used if this activity is somehow started without an index. */
    private static final int DEFAULT_TOPIC_INDEX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailRoot), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int topicIndex = getIntent().getIntExtra(MainActivity.EXTRA_TOPIC_INDEX, DEFAULT_TOPIC_INDEX);
        showTopic(topicIndex);
    }

    /**
     * Fills the detail layout with the data stored in arrays.xml for one topic.
     *
     * @param topicIndex index of the topic in the three arrays of arrays.xml
     */
    private void showTopic(int topicIndex) {
        Resources resources = getResources();

        // The three parallel arrays that hold all of the topic content.
        String[] titles = resources.getStringArray(R.array.string_array_titles);
        String[] contents = resources.getStringArray(R.array.string_array_content);
        String[] imageNames = resources.getStringArray(R.array.string_array_images);

        TextView title = findViewById(R.id.detailTitle);
        TextView content = findViewById(R.id.detailContent);
        ImageView image = findViewById(R.id.detailImage);

        title.setText(titles[topicIndex]);
        content.setText(contents[topicIndex]);

        // arrays.xml stores the drawable *name*, so turn it back into a resource id.
        // (The items are padded with spaces in the XML, hence the trim().)
        String imageName = imageNames[topicIndex].trim();
        image.setImageResource(
                resources.getIdentifier(imageName, DRAWABLE_TYPE, getPackageName()));
        image.setContentDescription(titles[topicIndex]);
    }
}
