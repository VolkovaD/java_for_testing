package kz.qa.jft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("8dc8e0c3d67467df6cb03da3f9b35813dd6e741f");
        RepoCommits commits = github.repos().get((new Coordinates.Simple("VolkovaD", "java_for_testing"))).commits();
        for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }

    }
}
