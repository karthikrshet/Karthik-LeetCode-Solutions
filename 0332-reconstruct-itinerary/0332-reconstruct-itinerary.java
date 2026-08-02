

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> flights = new HashMap<>();
        
        // Build the graph with destinations sorted lexicographically using PriorityQueue
        for (List<String> ticket : tickets) {
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            flights.get(ticket.get(0)).add(ticket.get(1));
        }
        
        LinkedList<String> itinerary = new LinkedList<>();
        dfs("JFK", flights, itinerary);
        return itinerary;
    }
    
    private void dfs(String airport, Map<String, PriorityQueue<String>> flights, LinkedList<String> itinerary) {
        PriorityQueue<String> targets = flights.get(airport);
        while (targets != null && !targets.isEmpty()) {
            dfs(targets.poll(), flights, itinerary);
        }
        // Add to the front of the list once all outgoing flights from this airport are exhausted
        itinerary.addFirst(airport);
    }
}