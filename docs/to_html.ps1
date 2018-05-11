Get-Item .\*.md | ForEach-Object {
  $from=$_.Name;
  $to=$_.BaseName+".html";
  pandoc -s -S -c .\css\base.css -c .\css\superfish-navbar-smoothness.css -c .\css\superfish-smoothness.css -B header.html -B menu.html -A footer.html -T "Team stateless - Enterprise Java CA 1" $from -o $to 
}