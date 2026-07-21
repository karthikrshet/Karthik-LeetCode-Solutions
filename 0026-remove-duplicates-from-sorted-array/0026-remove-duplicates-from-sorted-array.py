from typing import List

class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        # An empty list has 0 unique elements
        if not nums:
            return 0
            
        # write_index tracks where to place the next unique element
        write_index = 1
        
        # read_index scans the array starting from the second element
        for read_index in range(1, len(nums)):
            # If we find a new unique element
            if nums[read_index] != nums[read_index - 1]:
                # Overwrite the next available position in the unique section
                nums[write_index] = nums[read_index]
                write_index += 1
                
        # write_index now represents the length of the unique portion (k)
        return write_index