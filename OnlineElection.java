/*
 * Time complexity: O(n) for constructor and O(log n) for query.
 * Space complexity: O(n) for storing the vote counts and leaders.
 */

class TopVotedCandidate {
    HashMap<Integer, Integer> voteCount;
    HashMap<Integer, Integer> timeLeader;
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        if(persons == null || persons.length == 0) {
            return;
        }

        this.times = times;
        voteCount = new HashMap<>();
        timeLeader = new HashMap<>();
        int leader = persons[0];

        for(int i=0; i<persons.length; i++) {
            voteCount.put(persons[i], voteCount.getOrDefault(persons[i],0)+1);

            if(voteCount.get(persons[i]) >= voteCount.get(leader)) {
                leader = persons[i];
            }

            timeLeader.put(times[i],leader);
        }
    }
    
    public int q(int t) {

        if(timeLeader.containsKey(t)) {
            return timeLeader.get(t);
        }

        int low = 0;
        int high = times.length - 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(t < times[mid]) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return timeLeader.get(times[high]);
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */