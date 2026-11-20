package com.example.assignmentone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Home screen of CampusLife: banner, application title and the four topic buttons
 * (Facilities, Events, Clubs and Support).
 *
 * <p>Each button is wired up in XML with {@code android:onClick}, so both the
 * portrait layout ({@code res/layout/activity_main.xml}) and the landscape layout
 * ({@code res/layout-land/activity_main.xml}) use the same handlers. Every handler
 * confirms the click with a toast and then opens {@link DetailActivity} for the
 * topic that was tapped.</p>
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Key of the Intent extra that carries the index of the selected topic.
     * {@link DetailActivity} reads it back with {@link Intent#getIntExtra}.
     */
    public static final String EXTRA_TOPIC_INDEX =
            "com.example.assignmentone.extra.TOPIC_INDEX";

    // Positions of the topics inside res/values/arrays.xml. The three arrays in
    // that file (titles, content and image names) all use this same order, so a
    // single index is all DetailActivity needs to show the right topic.
    private static final int TOPIC_FACILITIES = 0;
    private static final int TOPIC_EVENTS = 1;
    private static final int TOPIC_CLUBS = 2;
    private static final int TOPIC_SUPPORT = 3;

    // Short captions of the topics, in the same order, used for the toast text.
    private static final int[] TOPIC_LABELS = {
            R.string.facilities, R.string.events, R.string.clubs, R.string.support
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /** onClick handler of the Facilities button (see activity_main.xml). */
    public void onFacilitiesClick(View view) {
        openTopic(TOPIC_FACILITIES);
    }

    /** onClick handler of the Events button (see activity_main.xml). */
    public void onEventsClick(View view) {
        openTopic(TOPIC_EVENTS);
    }

    /** onClick handler of the Clubs button (see activity_main.xml). */
    public void onClubsClick(View view) {
        openTopic(TOPIC_CLUBS);
    }

    /** onClick handler of the Support button (see activity_main.xml). */
    public void onSupportClick(View view) {
        openTopic(TOPIC_SUPPORT);
    }

    /**
     * Confirms the click with a toast and starts {@link DetailActivity}, passing the
     * index of the topic that should be displayed.
     *
     * @param topicIndex index of the topic in res/values/arrays.xml
     */
    private void openTopic(int topicIndex) {
        Toast.makeText(this,
                getString(R.string.topic_clicked, getString(TOPIC_LABELS[topicIndex])),
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_TOPIC_INDEX, topicIndex);
        startActivity(intent);
    }
}
