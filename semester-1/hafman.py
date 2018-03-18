import heapq
from collections import Counter

class Node:
  def __init__(self, left, right):
    self.left = left
    self.right = right
    
  def walk(self, code, acc):
    self.left.walk(code, acc + "0")
    self.right.walk(code, acc + "1")
    
  def __str__(self):
    return "Node ({} , {})".format(str(self.left), str(self.right))
    
  def len1 (self):
    return self.left.len1() + self.right.len1()  
  
  def __lt__(self, l1):
    return self.len1() < l1.len1()  
    
class Leaf:
  def __init__(self, s):
    self.s = s
    
  def walk(self, code, acc):
    code[self.s] = acc or "0"
    
  def __str__(self):
    return "Leaf ({})".format(self.s)
  
  def __lt__(self, l1):
    return self.len1() < l1.len1()
  
  def len1 (self):
    return len(self.s)
    
def huffman_encode(s):
  h = [(freq, Leaf(ch)) for ch, freq in Counter(s).items()]
  heapq.heapify(h)
  while len(h) > 1:
    freq1,n1 = heapq.heappop(h)
    freq2,n2 = heapq.heappop(h)
    heapq.heappush(h, (freq1 + freq2 ,Node(n1,n2)))
  [(_freq, root)] = h
  code = {}
  root.walk(code, "")
  return code
  
def main():
  s = input()
  code = huffman_encode(s)
  encoded = "".join(code[ch] for ch in s)
  print (len(code), len(encoded))
  for ch in sorted(code):
    print ("{}: {}".format(ch, code[ch]))
  print(encoded)

main()
