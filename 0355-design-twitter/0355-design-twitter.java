import java.util.*;

class Twitter {
    
    private static int timeStamp = 0;

    private static class Tweet {
        int tweetId;
        int time;
        Tweet next;

        public Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
            this.next = null;
        }
    }

    private static class User {
        int userId;
        Set<Integer> followed;
        Tweet head;

        public User(int userId) {
            this.userId = userId;
            this.followed = new HashSet<>();
            follow(userId); // User always follows themselves
        }

        public void follow(int id) {
            followed.add(id);
        }

        public void unfollow(int id) {
            if (id != userId) { // User cannot unfollow themselves
                followed.remove(id);
            }
        }

        public void post(int tweetId) {
            Tweet t = new Tweet(tweetId, timeStamp++);
            t.next = head;
            head = t;
        }
    }

    private Map<Integer, User> userMap;

    public Twitter() {
        userMap = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        userMap.putIfAbsent(userId, new User(userId));
        userMap.get(userId).post(tweetId);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!userMap.containsKey(userId)) return res;

        Set<Integer> followedUsers = userMap.get(userId).followed;
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> b.time - a.time);

        for (int id : followedUsers) {
            User u = userMap.get(id);
            if (u != null && u.head != null) {
                maxHeap.offer(u.head);
            }
        }

        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            Tweet t = maxHeap.poll();
            res.add(t.tweetId);
            count++;
            if (t.next != null) {
                maxHeap.offer(t.next);
            }
        }

        return res;
    }
    
    public void follow(int followerId, int followeeId) {
        userMap.putIfAbsent(followerId, new User(followerId));
        userMap.putIfAbsent(followeeId, new User(followeeId));
        userMap.get(followerId).follow(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) return;
        userMap.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_1 = obj.getNewsFeed(userId);
 * obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */