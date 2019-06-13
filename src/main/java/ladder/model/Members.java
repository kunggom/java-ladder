package ladder.model;

import java.util.ArrayList;
import java.util.List;

public class Members {
    private List<Member> members;

    public Members(List<String> names) {
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            members.add(new Member(names.get(i), i));
        }
        this.members = members;
    }

    void move(List<Row> ladder) {
        for (Member member : members) {
            member.move(ladder);
        }
    }

    public int size() {
        return members.size();
    }

    public List<Member> getMembers() {
        return members;
    }
}
