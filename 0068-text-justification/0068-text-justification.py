class Solution:
    def fullJustify(self, words: list[str], maxWidth: int) -> list[str]:
        res = []
        i = 0
        n = len(words)
        
        while i < n:
            # Find how many words can fit in the current line
            line_words = [words[i]]
            line_length = len(words[i])
            i += 1
            
            while i < n and line_length + 1 + len(words[i]) <= maxWidth:
                line_length += 1 + len(words[i])
                line_words.append(words[i])
                i += 1
                
            # If it's the last line or there is only one word in the line, left-justify
            if i == n or len(line_words) == 1:
                line_str = " ".join(line_words)
                line_str += " " * (maxWidth - len(line_str))
                res.append(line_str)
            else:
                # Fully justify the line
                total_chars = sum(len(w) for w in line_words)
                total_spaces = maxWidth - total_chars
                num_gaps = len(line_words) - 1
                
                spaces_per_gap = total_spaces // num_gaps
                extra_spaces = total_spaces % num_gaps
                
                line_str = ""
                for j in range(num_gaps):
                    # Distribute extra spaces to the leftmost gaps
                    g_spaces = spaces_per_gap + (1 if j < extra_spaces else 0)
                    line_str += line_words[j] + (" " * g_spaces)
                line_str += line_words[-1]  # Append the last word without trailing space
                res.append(line_str)
                
        return res