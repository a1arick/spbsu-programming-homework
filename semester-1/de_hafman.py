def huffmanDecode (dictionary, text):
  res = ""
  while text:
    for k in dictionary:
      if text.startswith(k):
         res += dictionary[k]
         text = text[len(k):]
  return res

n, m = map(lambda x: int(x), input().split())
ch = { k: v for v, k in [input().split(": ") for i in range(n)]}
print (huffmanDecode(ch, input()))