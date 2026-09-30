package edu.dsu.scd.lms;

public final class Member {
    private final String name;
    private final String memberId;

    public Member(String name, String memberId) {
        if (name == null || name.isBlank() || memberId == null || memberId.isBlank()) {
            throw new InvalidInputException("Member name and ID are required.");
        }
        this.name = name.trim();
        this.memberId = memberId.trim();
    }

    public String getName() { return name; }
    public String getMemberId() { return memberId; }
}
