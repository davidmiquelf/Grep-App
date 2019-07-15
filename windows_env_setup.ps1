Write-Output '$ENV:tokenSecret += "<YOUR TOKEN SECRET>" ' | Out-File $Profile -encoding ascii
Write-Output '$ENV:accessToken += "<YOUR ACCESS SECRET>" ' | Out-File $Profile -append -encoding ascii
Write-Output '$ENV:consumerSecret += "<YOUR CONSUMER SECRET>"' | Out-File $Profile -append -encoding ascii
Write-Output '$ENV:consumerKey += "<YOUR CONSUMER KEY>"' | Out-File $Profile -append -encoding ascii
Write-Output '$ENV:spring_profiles_active += "dev"' | Out-File $Profile -append -encoding ascii
& $profile